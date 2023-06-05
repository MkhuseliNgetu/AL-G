#!/bin/bash

#The Purpose of this script is to move the Drive Attribute Data into the project files of the application.
MainUser=$(id -nu "1000")
CopyToProjectFilesLocation=$(cp /home/"${MainUser}"/SMART.txt ~/NetBeansProjects/AL-G)
#RemoveSMARTDataHome="$(rm SMART.txt)"

#Checking if the script has ran successfully
if [[ "${?}" -eq 0 ]]
then
    echo 'SMART data has been moved to project files successfully'
elif [[ "${?}" -ne 0 ]]
then
    echo 'SMART data was not moved to project files successfully'
    exit 1
fi
