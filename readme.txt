readme.txt

:Author: kalipy
:Email: kalipy@debian
:Date: 2020-11-25 21:43

find ./src -iname "*.java" > cscope.file //建议cscope的只加src，不要加入框架和jdk的源码，这样cscope用来搜索自己写的java代码,ctags用来搜索跳转框架和jdk的源码

cscope -bkq -i cscope.file  //-i 指定输入文件

find ~/.gradle -iname "activemq*.jar"

cp /home/kalipy/.gradle/caches/modules-2/files-2.1/org.apache.activemq/activemq-all/5.9.1/a2b5134755895edc9e0217f1998467d0bfbfe833/activemq-all-5.9.1.jar gg/.

cd gg/ 

unzip activemq-all-5.9.1.jar

ctags -R

见：
ctags和cscope的java代码跳转.md
