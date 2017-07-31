# -*- mode: ruby -*-
# vi: set ft=ruby :
Vagrant.configure(2) do |config|
  if (/cygwin|mswin|mingw|bccwin|wince|emx/ =~ RUBY_PLATFORM) != nil
    config.vm.synced_folder ".", "/vagrant", mount_options: ["dmode=700,fmode=600"]
  else
    config.vm.synced_folder ".", "/vagrant"
  end  
  (1..2).each do |i|
    config.vm.define "nginx-#{i}" do |d|
      d.vm.box = "bento/centos-7.3" 
      d.vm.hostname = "nginx-#{i}"
      x = 0
      d.vm.network "private_network", ip: "192.168.77.10#{i+x}"    
      d.vm.provider "virtualbox" do |v|
        v.memory = 2048
      end
      if (i == 1)
         d.vm.provision :shell, path: "scripts/bootstrap4CentOs_ansible.sh"
#         d.vm.provision :shell, inline: "PYTHONUNBUFFERED=1  ansible-playbook /vagrant/ansible/playbook.yml  -i  /vagrant/ansible/hosts/all " 
      end 
    end
  end
  (1..2).each do |i|
    config.vm.define "ap-#{i}" do |d|
      d.vm.box = "bento/ubuntu-16.04"
      d.vm.hostname = "ap-#{i}"
      x = 2
      d.vm.network "private_network", ip: "192.168.77.10#{i+x}"    
      d.vm.provider "virtualbox" do |v|
        v.memory = 2048
      end     
    end
  end
  (3..7).each do |i|
    config.vm.define "ap-#{i}" do |d|
      d.vm.box = "bento/centos-7.3" 
      d.vm.hostname = "ap-#{i}"
      x = 2
      d.vm.network "private_network", ip: "192.168.77.10#{i+x}"    
      d.vm.provider "virtualbox" do |v|
        v.memory = 2048
      end     
    end
  end  
  if Vagrant.has_plugin?("vagrant-cachier")
    config.cache.scope = :box
  end
  if Vagrant.has_plugin?("vagrant-vbguest")
    config.vbguest.auto_update = false
    config.vbguest.no_install = false
    config.vbguest.no_remote = false
  end
end