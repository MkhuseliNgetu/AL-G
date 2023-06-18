#!/bin/bash

#Check if the user is running the script with admin rights.
CurrentUserID=$(id -u)
if [[ "${CurrentUserID}" -eq 0 ]]
then
    echo 'This script must not to be run as root (su) to proceed'
    exit 1
fi

#Get S.M.A.R.T data
ChosenHDD="${2}"
FindSMARTData=$(echo "${1}" | sudo -S smartctl -a /dev/${ChosenHDD} >> Holding.txt && cat Holding.txt | sed -n '57,82p' >> SMART.txt)
MakeUsable=$(chmod 666 SMART.txt)
CopyToHomeLocation=$(cp -p SMART.txt /home)
RemoveSMARTDataFromCurrentLocation=$(rm Holding.txt && rm SMART.txt)

#Checking if the script has ran successfully
if [[ "${?}" -eq 0 ]]
then
    echo 'SMART data has been obtained successfully'
elif [[ "${?}" -ne 0 ]]
then
    echo 'SMART data was not obtained successfully'
    exit 1
fi
