#!/bin/bash

#The purpose of this script is to get all of the connected and decrypted hard drives
CurrentUserID=$(id -u)
if [[ "${CurrentUserID}" -eq 0 ]]
then
    echo 'This script should not be run with root (su). Please run the script as a not root user'
    exit 1
fi

#Get Hard drives
GetMyDrives=$(lsblk -o NAME,MOUNTPOINTS > DriveOutputs.txt)
CopyToDesiredLocation=$(cp -p DriveOutputs.txt ~/Documents/GitHub/AL-G)
RemoveDriveOutputsFromCurrentLocation=$(rm DriveOutputs.txt)

#Checking if the script has ran successfully
if [[ "${?}" -eq 0 ]]
then
    echo 'Hard Drives have been detected successfully and basic data has been obtained successfully'
elif [[ "${?}" -ne 0 ]]
then
    echo 'Hard Drives were not detected successfully and basic data has not been obtained successfully'
    exit 1
fi
