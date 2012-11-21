Hadoop Building
===============

The purpose of this project is to create an automated provisioning system for hadoop with vagrant.

Requirements
-
* Download and install VirtualBox:  https://www.virtualbox.org/wiki/Downloads
* Within VirtualBox Manager `VirtualBox`, define a HOST-ONLY network: `vboxnet0`
* Download and install Vagrant:  http://vagrantup.com/
* Download and install git client:  http://git-scm.com/

Installation
-------------
1. Clone the git repo:  https://github.com/jeffreylutz/hadoop
2. Chdir into the hadoop directory
3. Download virtualbox (hadoop.box) image:  http://www.filedropper.com/hadoop
4. Run `vagrant box add 'hadoop.box' 'hadoop'`
5. Run `vagrant up`  --> You should have four VMs running in VirtualBox
6. Now, let's install Cloudera Manager v4.  `vagrant ssh master`
7. Run the installer: `sudo /vagrant/cloudera-manager-installer.bin`
8. When asked for nodes/search for nodes, enter: `192.168.56.[10-13]` The installer should find four nodes to install.

You should now have a complete Hadoop cluster setup and running.  You can run the sample applications or your own by `vagrant ssh master` and running `hadoop <cmd> ...`

See Also
-----------
More Hadoop information can be found here:  https://ccp.cloudera.com/display/DOC/Hadoop+Tutorial

Let me know if there are any issues/problems by creating an issue at http://github.com/jeffreylutz/hadoop

