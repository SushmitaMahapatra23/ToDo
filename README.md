# ToDo App
### To add jacoco, follow the below steps:-
### 1. Add below line inside plugins section of build.gradle.kts file
```
id("jacoco") 
```
### 2. Then there are two ways to generate jacoco reports 
**First Method:- Run following command**
```
gradle clean build

./gradlew jacocoTestReport
```


**Second Method:-**
Add following code in build.gradle.kts

```
tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}
```
`Now, run cmd "gradle clean build", it will also generate jacocoreports
`



jacocoReports are generated under build folder -> reports -> jacoco -> html -> index.html


