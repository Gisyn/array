import numpy as np
import cv2 as cv
import matplotlib.pyplot as plt
''''
img=cv.imread('touxiang.jpg',0)

cv.imshow('image',img)##cv的显示
cv.waitKey(0)
cv.destroyAllWindows()

#plt.imshow(img[:,:])#matplotlib的显示
plt.imshow(img,camp=plt.cm.gray)#灰度显示  grey报错
plt.title('匹配结果')#中文打印不出来
plt.xticks([])
plt.yticks([])
plt.show()

cv.imwrite('graytouxiang.png',img)
'''

''''
#创建个空白图像
img=np.zeros((512,512,3),np.uint8)
#绘制图像
cv.line(img,(0,0),(511,511),(255,0,0),5)
cv.rectangle(img,(384,0),(510,128),(0,255,0),3)
cv.circle(img,(447,63),63,(0,0,255),-1)
font=cv.FONT_HERSHEY_SIMPLEX
cv.putText(img,'OpenCV',(10,500),font,4,(255,255,255),2,cv.LINE_AA)
#图像展示
plt.imshow(img[:,:,:])
plt.title('result')
plt.xticks([])
plt.yticks([])
plt.show()
'''
