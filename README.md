# ToDo App new 3
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

mm
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

wdw

jacocoReports are generated under build folder -> reports -> jacoco -> html -> index.html

### To install redis through docker run below commands in terminal
```dockerfile
docker run -d --name some-redis -p 6379:6379 redis
```
```
docker exec -it some-redis
```
```
redis-cli
```


test
