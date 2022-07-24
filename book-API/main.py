import cv2
import torch
from flask import Flask, request
from PIL import Image
from io import BytesIO

app = Flask(__name__)

# model読み込み
model = torch.hub.load('ultralytics/yolov5', 'custom', path='best.pt')

@app.route("/", methods=["POST"])

def get():
	# 写真読み込み
	img = Image.open(BytesIO(request.data))
	

	# 推論
	results = model(img)
	d = []
	dic = {}
	for value in results.pandas().xyxy[0].name:
  		dic['result'] = value
  		d.append(dic)
	message = '本はありません'
	if len(d) != 0:
		message = ''
		for i in d:
			message  = message + i.get("result") + '\n'

		message = message.rstrip('/r/n')

	return message
  		

if __name__ == "__main__":
    app.run(debug=True)