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
    num = 0
    for value in results.pandas().xyxy[0].name:
        key = 'result' + str(num)
        dic[key] = value
        d.append(dic)
        num += 1
    message = '本はありません'
    num = 0
    if len(d) != 0:
        message = ''
        for i in d:
            message  = message + i.get("result{}".format(str(num))) + '\n'
            num += 1

        message = message.rstrip('/r/n')

    return message


if __name__ == "__main__":
    app.run(host='0.0.0.0', debug=True)
