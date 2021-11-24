#图像的加法
rain=cv.imread('rain.jpg',1)
view=cv.imread('view.jpg',1)
plt.imshow(rain[:, :, ::-1])
plt.show()
plt.imshow(view[:, :, ::-1])
plt.show()
print(rain.shape,view.shape)
rain1=cv.resize(rain,(720,960))#别弄反了
print(rain1.shape,view.shape)
img1=cv.add(rain1,view)
img2=rain1+view
plt.imshow(img1[:,:,::-1])
plt.show()
plt.imshow(img2[:,:,::-1])
plt.show()

#图像的混合 g(x)=(1-a)f1(x)+af2(x)+b
img3=cv.addWeighted(rain1,0.7,view,0.3,0)
plt.imshow(img3[:,:,::-1])
plt.show()
