Now that we are running instance of the Jenkins at ‘http://192.168.99.100:8080/‘ (Assuming 192.168.99.100 is your Docker-Machine or equivalent IP), we can setup up the things

Admin Password Jenkins 2.0 will ask for the Admin password stored in the Jenkins Master container. We can get it and paste it in the console

   $ docker exec jenkins  cat /var/jenkins_home/secrets/initialAdminPassword
   
User Details The next step is to fill in the required user details in order to login into the Jenkins.

Plugins

You can then install default plugins
