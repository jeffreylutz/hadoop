Hadoop Building
===============

The purpose of this project is to create an automated provisioning system for hadoop with vagrant.

PSUEDO-CLUSTER MODE
=============
Requirements
-------------
* Download and install git client:  http://git-scm.com/
* Download and install VirtualBox:  https://www.virtualbox.org/wiki/Downloads
* Download the VirtualBox CDH 4 image:  https://ccp.cloudera.com/display/SUPPORT/Cloudera%27s+Hadoop+Demo+VM+for+CDH4 or http://192.168.1.10/cloudera-demo-vm-cdh4.1.1-virtualbox.tar.gz

Installation
------------
1. Start VirtualBox Manager
2. Select:  Machines->New... item under the menu
3. Go through the wizard:
	name:    <some_name>
	type:    linux
	version: 64-bit
        memory size: 1.0 to 2.0 GB
	hard drive: <select the downloaded .vmdk file>
4. Power on the newly created 


FULL CLUSTER MODE
=============
Requirements
-------------
* Download and install VirtualBox:  https://www.virtualbox.org/wiki/Downloads
* Within VirtualBox Manager `VirtualBox`, define a HOST-ONLY network: `vboxnet0`
* Download and install Vagrant:  http://vagrantup.com/
* Download and install git client:  http://git-scm.com/

Installation
-------------
1. Clone the git repo:  https://github.com/jeffreylutz/hadoop
2. Chdir into the hadoop directory of the local filesystem.
3. Download virtualbox (hadoop.box) image:  https://docs.google.com/open?id=0BxqNuLQvELJ-dDZzbDlBeEJlS0k
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

