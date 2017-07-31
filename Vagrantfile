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
      d.vm.network "public_network", bridge: "eno4", ip: "192.168.57.10#{i+x}", netmask: "255.255.255.0" , gateway: "192.168.57.1"
      d.vm.provision :shell, inline: " sudo route delete default; sudo route add default gw 192.168.57.1 dev enp0s8 "       
      # d.vm.network "private_network", ip: "192.168.77.10#{i+x}"    
      d.vm.provider "virtualbox" do |v|
        v.memory = 2048
      end
      if (i == 1)
         d.vm.provision :shell, path: "scripts/bootstrap4CentOs_ansible.sh"
#         d.vm.provision :shell, inline: "PYTHONUNBUFFERED=1  ansible-playbook /vagrant/ansible/playbook.yml  -i  /vagrant/ansible/hosts/all " 
      end 
    end
  end
  (1..7).each do |i|
    config.vm.define "ap-#{i}" do |d|
      if (i == 1 || i == 2 )
          d.vm.box = "bento/ubuntu-16.04"
      else
          d.vm.box = "bento/centos-7.3" 
      end
      d.vm.hostname = "ap-#{i}"
      x = 2
      d.vm.network "public_network", bridge: "eno4", ip: "192.168.57.10#{i+x}", netmask: "255.255.255.0" , gateway: "192.168.57.1"      
      # d.vm.network "private_network", ip: "192.168.77.10#{i+x}"    
      d.vm.provider "virtualbox" do |v|
        v.memory = 2048
      end
      d.vm.provision :shell, inline: " sudo route delete default; sudo route add default gw 192.168.57.1 dev enp0s8 " 
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
