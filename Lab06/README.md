# Lab 6

| Outline | Value |
| --- | --- |
| Course | SEG 3103 |
| Date | Summer 2021 |
| Name | Gabe Cordovado (300110852) and Michael Kagnew(300113347)  |
| Professor | Andrew Forward, aforward@uottawa.ca |
| TA | Zahra Kakavand, zkaka044@uottawa.ca|

Repo link https://github.com/Michael-Kagnew/seg3103_playground

# Mvn --version output
```
Apache Maven 3.8.1 (05c21c65bdfed0f71a2f2ada8b84da59348c4c5d)
Maven home: C:\Users\Michael\apache-maven\bin\..
Java version: 11.0.11, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk-11.0.11
Default locale: en_CA, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```
# Mvn compile output
```
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------------------< SEG3103:BookstoreApp >------------------------
[INFO] Building BookstoreApp 0.1.0
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ BookstoreApp ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory C:\Users\Michael\Documents\Coding Projects\Uottawa\SEG3103\Labs\seg3103_playground\Lab06\BookstoreApp\src\main\resources
[INFO]
[INFO] --- maven-compiler-plugin:3.7.0:compile (default-compile) @ BookstoreApp ---
[INFO] Nothing to compile - all classes are up to date
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.049 s
[INFO] Finished at: 2021-07-13T17:19:19-04:00
[INFO] ------------------------------------------------------------------------
```
# mvn package -DskipTests output
```
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------------------< SEG3103:BookstoreApp >------------------------
[INFO] Building BookstoreApp 0.1.0
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ BookstoreApp ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory C:\Users\Michael\Documents\Coding Projects\Uottawa\SEG3103\Labs\seg3103_playground\Lab06\BookstoreApp\src\main\resources
[INFO]
[INFO] --- maven-compiler-plugin:3.7.0:compile (default-compile) @ BookstoreApp ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ BookstoreApp ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory C:\Users\Michael\Documents\Coding Projects\Uottawa\SEG3103\Labs\seg3103_playground\Lab06\BookstoreApp\src\test\resources
[INFO]
[INFO] --- maven-compiler-plugin:3.7.0:testCompile (default-testCompile) @ BookstoreApp ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- maven-surefire-plugin:2.22.1:test (default-test) @ BookstoreApp ---
[INFO] Tests are skipped.
[INFO]
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ BookstoreApp ---
[INFO] 
[INFO] --- maven-assembly-plugin:2.2-beta-5:single (make-assembly) @ BookstoreApp ---

...Lots of skipped lines here

[WARNING] Configuration options: 'appendAssemblyId' is set to false, and 'classifier' is missing.
Instead of attaching the assembly file: C:\Users\Michael\Documents\Coding Projects\Uottawa\SEG3103\Labs\seg3103_playground\Lab06\BookstoreApp\target\BookstoreApp-0.1.0.jar, it will become the file for main project artifact.
NOTE: If multiple descriptors or descriptor-formats are provided for this project, the value of this file will be non-deterministic!
[WARNING] Replacing pre-existing project main-artifact file: C:\Users\Michael\Documents\Coding Projects\Uottawa\SEG3103\Labs\seg3103_playground\Lab06\BookstoreApp\target\BookstoreApp-0.1.0.jar
with assembly file: C:\Users\Michael\Documents\Coding Projects\Uottawa\SEG3103\Labs\seg3103_playground\Lab06\BookstoreApp\target\BookstoreApp-0.1.0.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  21.458 s
[INFO] Finished at: 2021-07-13T17:26:32-04:00
[INFO] ------------------------------------------------------------------------
```

This command compiles the latest file changes and packages into an execuable based off settings in the pom.xml file.

# java -jar ./target/BookstoreApp-0.1.0.jar Output
```
Press Enter to stop server

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.1.4.RELEASE)

2021-07-13 17:55:40.689  INFO 17924 --- [           main] seg3102.bookstore.Bookstore1Application  : Starting Bookstore1Application v0.0.1-SNAPSHOT on DESKTOP-SUSK29O with PID 17924 (C:\Users\Michael\Documents\Coding Projects\Uottawa\SEG3103\Labs\seg3103_playground\Lab06\BookstoreApp\bookstore5.jar started by Michael in C:\Users\Michael\Documents\Coding Projects\Uottawa\SEG3103\Labs\seg3103_playground\Lab06\BookstoreApp)
2021-07-13 17:55:40.694  INFO 17924 --- [           main] seg3102.bookstore.Bookstore1Application  : No active profile set, falling back to default profiles: default
2021-07-13 17:55:42.436  INFO 17924 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data repositories in DEFAULT mode.
2021-07-13 17:55:42.559  INFO 17924 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 106ms. Found 1 repository interfaces.
2021-07-13 17:55:43.385  INFO 17924 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration' of type [org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration$$EnhancerBySpringCGLIB$$71e7d0e] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2021-07-13 17:55:44.273  INFO 17924 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2021-07-13 17:55:44.333  INFO 17924 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2021-07-13 17:55:44.334  INFO 17924 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.17]
2021-07-13 17:55:44.506  INFO 17924 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2021-07-13 17:55:44.507  INFO 17924 --- [           main] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 3699 
ms
2021-07-13 17:55:44.834  INFO 17924 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2021-07-13 17:55:45.259  INFO 17924 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2021-07-13 17:55:45.396  INFO 17924 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [
        name: default
        ...]
2021-07-13 17:55:45.532  INFO 17924 --- [           main] org.hibernate.Version                    : HHH000412: Hibernate Core {5.3.9.Final}
2021-07-13 17:55:45.539  INFO 17924 --- [           main] org.hibernate.cfg.Environment            : HHH000206: hibernate.properties not found
2021-07-13 17:55:45.825  INFO 17924 --- [           main] o.hibernate.annotations.common.Version   : HCANN000001: Hibernate Commons Annotations {5.0.4.Final}
2021-07-13 17:55:46.293  INFO 17924 --- [           main] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.H2Dialect
2021-07-13 17:55:47.218  INFO 17924 --- [           main] o.h.t.schema.internal.SchemaCreatorImpl  : HHH000476: Executing import script 'org.hibernate.tool.schema.internal.exec.ScriptSourceInputNonExistentImpl@3f9f71ff'
2021-07-13 17:55:47.224  INFO 17924 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2021-07-13 17:55:47.862  WARN 17924 --- [           main] o.s.security.core.userdetails.User       : User.withDefaultPasswordEncoder() is considered unsafe for production and is only intended for sample applications.
2021-07-13 17:55:48.271  INFO 17924 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2021-07-13 17:55:48.379  WARN 17924 --- [           main] aWebConfiguration$JpaWebMvcConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2021-07-13 17:55:49.050  INFO 17924 --- [           main] o.s.s.web.DefaultSecurityFilterChain     : Creating filter chain: any request, [org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@42a0501e, org.springframework.security.web.context.SecurityContextPersistenceFilter@59fbb34, org.springframework.security.web.header.HeaderWriterFilter@2f4ba1ae, org.springframework.security.web.authentication.logout.LogoutFilter@3d19d85, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter@5762658b, org.springframework.security.web.savedrequest.RequestCacheAwareFilter@3a8d467e, org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@6ed043d3, org.springframework.security.web.authentication.AnonymousAuthenticationFilter@6aa3bfc, org.springframework.security.web.session.SessionManagementFilter@3762c4fc, org.springframework.security.web.access.ExceptionTranslationFilter@1f67761b, org.springframework.security.web.access.intercept.FilterSecurityInterceptor@6fc1020a]
2021-07-13 17:55:49.236  INFO 17924 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2021-07-13 17:55:49.245  INFO 17924 --- [           main] seg3102.bookstore.Bookstore1Application  : Started Bookstore1Application in 9.335 seconds (JVM running for 10.192)

```
# Mvn test Output

![image](assets/test_run_before_new.png)

# Screenshot of added test
![image](assets/extra_tst.png)

# App Running Screenshot

Below is the app running when executing the BookStoreApp-0.1.0.jar file in the target directory
![image](assets/app_run.png)

# Tests Running with New Test
The below screenshot is the output of the 3 tests that exist, plus one extra one we created.

![image](assets/test_run.png)