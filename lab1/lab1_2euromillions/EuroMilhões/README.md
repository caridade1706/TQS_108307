#Euromillions-play

##Tests that should be written for the BoundedSetOfNaturals class:

- Test the add function with Illegal add attempts:
        - testAddElement()
        - testAddFromBadArray()
        - testAddDuplicate()
        - testAddNonNatural()
- Test the intersect method.
- Test the hashcode method.
- Test the equals method.


##Run Jacoco coverage analysis
```
mvn clean test jacoco:report
```

Before
![overview_before](./prints/Before1.png.png)
![methods_before](./prints/Before2.png.png)

After
![overview_after](./prints/After1.png.png)
![methods_after](./prints/After2.png.png)

