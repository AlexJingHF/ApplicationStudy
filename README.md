ApplicationStudy
================

Test applicatin of Application

<font size="7" color="red">What is Application</font>

Application和Actovotu,Service一样是android框架的一个系统组件，当android程序启动时系统会创建一个 application对象，用来存储系统的一些信息。通常我们是不需要指定一个Application的，这时系统会自动帮我们创建，如果需要创建自己 的Application，也很简单创建一个类继承 Application并在manifest的application标签中进行注册(只需要给Application标签增加个name属性把自己的 Application的名字定入即可)。
android系统会为每个程序运行时创建一个Application类的对象且仅创建一个，所以Application可以说是单例 (singleton)模式的一个类.且application对象的生命周期是整个程序中最长的，它的生命周期就等于这个程序的生命周期。因为它是全局 的单例的，所以在不同的Activity,Service中获得的对象都是同一个对象。所以通过Application来进行一些，数据传递，数据共享 等,数据缓存等操作。

Data passing between components using Application

假如有一个Activity A, 跳转到 Activity B ,并需要推荐一些数据，通常的作法是Intent.putExtra() 让Intent携带，或者有一个Bundle把信息加入Bundle让Intent推荐Bundle对象，实现传递。但这样作有一个问题在 于，Intent和Bundle所能携带的数据类型都是一些基本的数据类型，如果想实现复杂的数据传递就比较麻烦了，通常需要实现 Serializable或者Parcellable接口。这其实是Android的一种IPC数据传递的方法。如果我们的两个Activity在同一个 进程当中为什么还要这么麻烦呢，只要把需要传递的对象的引用传递过去就可以了。
基本思路是这样的。在Application中创建一个HashMap，以字符串为索引，Object为value这样我们的HashMap就可以存储任何类型的对象了。在Activity A中把需要传递的对象放入这个HashMap，然后通过Intent或者其它途经再把这人索引的字符串传递给Activity B ,Activity B 就可以根据这个字符串在HashMap中取出这个对象了。只要再向下转个型 ，就实现了对象的传递。

Data caching in Application

我一般会习惯在application中建立两个HashMap一个用于数据的传递，一个用于缓 存一些数据。比如有一个Activity需要从网站获取一些数据，获取完之后我们就可以把这个数据cache到Application 当中，当页面设置到其它Activity再回来的时候，就可以直接使用缓存好的数据了。但如果需要cache一些大量的数据，最好是cache一些 (软引用)SoftReference ，并把这些数据cache到本地rom上或者sd卡上。如果在application中的缓存不存在，从本地缓存查找，如果本地缓存的数据也不存在再从网 络上获取。
