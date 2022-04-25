#!/bin/sh

echo "Installing and configuring mariadb"

# Mirem si hi han actualitzacions, i instal·lem el mariadb amb la comanda següent
apt-get update
apt-get install mariadb-server -y

sudo systemctl enable mariadb
sudo systemctl start mariadb

root_password=mypass

sudo mysql -e "UPDATE mysql.user SET Password = PASSWORD('$root_password') WHERE User = 'root'"

sudo mysql -e "DROP USER IF EXISTS ''@'localhost'"
sudo mysql -e "DROP USER IF EXISTS ''@'$(hostname)'"
sudo mysql -e "DROP DATABASE IF EXISTS test"

echo "Creant base de dades..."
sudo mysql -e "CREATE DATABASE IF NOT EXISTS loghelperdb"

echo "Creant usuari..."
sudo mysql -e "CREATE USER 'loghelper'@localhost IDENTIFIED BY '1234'"

echo "Donant permisos a usuari..."
sudo mysql -e "GRANT ALL PRIVILEGES ON *.* TO 'loghelper'@localhost IDENTIFIED BY '1234'"
sudo mysql -e "FLUSH PRIVILEGES"
