To install into local mvn repository

mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file -Dfile=framework-5.2.0.jar -DgroupId=org.insight-centre -DartifactId=framework -Dversion=5.2.0 -Dpackaging=jar -DgeneratePom=true
mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file -Dfile=ILOG.CP.jar -DgroupId=com.ibm.ilog -DartifactId=cpoptimizer -Dversion=22.1.1 -Dpackaging=jar -DgeneratePom=true
