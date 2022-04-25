# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|
  config.vm.box = "debian/bullseye64"
  config.vm.hostname = "lhmariadb"
  config.vm.define "lhmariadb"
  config.vm.network "private_network", ip: "172.16.0.100"
  config.vm.network "forwarded_port", guest: 3306, host: 3306
  config.vm.provision "shell", path: "bootstrap.sh"
  config.vm.provider "virtualbox" do |vb|
	vb.name = "lhmariadb"
    vb.memory = "512"
    vb.cpus = 1
  end
end