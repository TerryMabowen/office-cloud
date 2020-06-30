filepath="/Users/apple_22/.m2/repository"
function getFileList(){
  cd $filepath;
  fileList=`ls $filepath`;
  for dir in $fileList; do
    filename="$filepath/$dir";
    echo $filename;
#    if [ -f $filename ]; then
#      echo "$filename 是文件";
#    elif [ -d $filename ]; then
#      echo "$filename 是目录";
#    else
#      echo "$filename is a invalid path";
#    fi
  done
}

getFileList;