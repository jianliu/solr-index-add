source /etc/profile

exec java -Dfile.encoding=UTF-8 -cp conf:$(echo $(ls lib/*) | sed 's/ /:/g') -Xms512m -Xmx2048m com.zhe800.deal.title.search.TaskRun $1

