#!/bin/sh

echo "Installing and configuring mariadb"

# Mirem si hi han actualitzacions, i instal·lem el mariadb amb la comanda següent
apt-get update
apt-get install mariadb-server -y

ifconfig -a

sudo systemctl enable mariadb
sudo systemctl start mariadb

root_password=mypass

sudo mysql -e "UPDATE mysql.user SET Password = PASSWORD('$root_password') WHERE User = 'root'"

sudo mysql -e "DROP USER IF EXISTS ''@'localhost'"
sudo mysql -e "DROP USER IF EXISTS ''@'$(hostname)'"
sudo mysql -e "DROP DATABASE IF EXISTS test"

echo "Creant base de dades..."
sudo mysql -e "CREATE DATABASE IF NOT EXISTS loghelperdb"

echo "Configuració de mariadb"
cp /vagrant/50-server.cnf /etc/mysql/mariadb.conf.d

echo "Creant usuari i donant permisos"
mysql << EOF
CREATE OR REPLACE USER loghelper@'%' IDENTIFIED BY '1234';
GRANT ALL ON *.* TO loghelper@'%';
EOF

systemctl restart mariadb
