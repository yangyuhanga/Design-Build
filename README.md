# Design-Build
London Queen Mary University 2021 Design &amp; Build Results
## IoT
### 数据
#### 训练数据
为了训练一个有效的目标检测模型，我们收集了3523张钥匙、魔方和书籍的图像，并在labellmg软件上对他进行了标注，并保存为YOLO格式。同时，我们考虑了各种环境设置、照明条件和角度，在原数据集基础上调整了这些参数，产生了明暗不同背景的版本（具体调整参数会在之后说明），以提高训练精度。训练数据保存在data/train文件夹下
#### 验证数据
验证数据集是独立于训练数据集的，我们又重新搜集了592张图片作为我们的验证集，去判断模型是否完善。验证数据保存在data/valid文件夹下

### 模型训练
我们整个过程一共训练出了三个模型，分别为best1.pt，best2.pt，best3.pt，其中best3模型效果最好，这些模型都保存在model文件夹下。
#### best1.pt
我们以yolov8n.pt为基底模型，在data/train数据集下训练三百轮；
#### best2.pt
我们以yolov8x.pt为基底模型，在data/train数据集下训练三百轮；
#### best3.pt
我们以best2.pt为基底模型，对于best2模型的一些不足之处进行了微调；
同时，我们对best3模型在data/valid数据集上进行评估，评估指标包括准确性、召回率、F1分数和IoU等，最终结果保存在了evaluate文件夹下。

### 模型推理
