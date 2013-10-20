#!/bin/bash
for file in /Users/mohsinjunaid/androApps/BOOKS_AND_REFERENCE/*
do
   echo "file name $file..."
   fileName=${file##*/}
   dirName=`basename $fileName`
   echo $dirName
   dirPath="/Users/mohsinjunaid/androApps/BOOKS_AND_REFERENCE_OUTPUT"
   outputFile="$dirPath/$fileName.txt"
   if [ ! -f $outputFile ] 
   then 
       txt="python /Volumes/myPassport/androguard/cfgAndroFile.py -i $file -o $outputFile"
       $txt
   fi 
done


