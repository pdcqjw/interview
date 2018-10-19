#!/usr/bin/env bash
set -x
set -e

groupadd webgroup ||
useradd -g webgroup webusr ||
sudo echo "webusr:pass" | chpasswd

mkdir /appl
mkdir /appl/installs
mkdir /appl/projects
mkdir /appl/scripts
mkdir /appl/mongo
mkdir /appl/mongo/data

cd /appl
chown webusr.webgroup projects
chmod 755 projects
chown webusr.webgroup scripts
chmod 755 scripts

# 项目文件夹，注意项目文件夹是用webusr用户来创建的
su webusr
cd /appl/projects
mkdir theta
mkdir theta/www

sudo apt-get install mongodb
sudo apt-get install git

mkdir -r /www/
pip install virtualenv
virtualenv venv
source venv/bin/activate

su webusr
cd /appl/projects/theta
rm -Rf www
tar -zxvf pro