http://localhost:8080/EnterpriseApplication1-war/NewServlet/getimagedirs
http://localhost:8080/EnterpriseApplication1-war/NewServlet/getdirimages?directory=pics\/pics02




Glassfish:::
my.imgviewer.imgdir  C:\VBox\shared_fedora\imgviewer\
my.imgviewer.urlpathroot  http://localhost:8080/imgviewer/

Netbeans:::
my.imgviewer.imgdir  C:\VBox\shared_fedora\imgviewer\
my.imgviewer.urlpathroot  http://localhost:8080/EnterpriseApplication1-war/imgviewer/

WildFly:::
my.imgviewer.imgdir  /home/shared/imgviewer
my.imgviewer.urlpathroot  http://localhost:8080/imgviewer/
http://localhost:8080/EnterpriseApplication1-war/imgviewer/pics/pics01/JADED.jpg



How to configure Wildfly to serve static content (like images)?

Add another file handler and another location to the undertow subsystem in standalone.xml:

<server name="default-server">
    <http-listener name="default" socket-binding="http"/>
    <host name="default-host" alias="localhost">
        <location name="/" handler="welcome-content"/>
        <location name="/img" handler="images"/>
    </host>
</server>
<handlers>
    <file name="welcome-content" path="${jboss.home.dir}/welcome-content" directory-listing="true"/>
    <file name="images" path="/var/images" directory-listing="true"/>
</handlers>