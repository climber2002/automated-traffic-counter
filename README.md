Introduction
------------

This is a Scala Solution to AIPS Coding Challenge. It's a standard sbt project. Firstly make sure (SBT)(https://www.scala-sbt.org/) is installed. And then after extracting the zip file, `cd` to the project folder and run `sbt run`, it will read the sample file `src/main/resources/timeFrames.txt` and give following outputs,

```
The number of cars seen in total is 398

The total number of cars by date:
2016-12-01 179
2016-12-05 81
2016-12-08 134
2016-12-09 4

The top 3 half hours with most cars:
2016-12-01T07:30:00 46
2016-12-01T08:00:00 42
2016-12-08T18:00:00 33

The 1.5 hour period with least cars starts from 2016-12-01T05:00:00, and the total number of cars is 31:
2016-12-01T05:00:00 5
2016-12-01T05:30:00 12
2016-12-01T06:00:00 14
```

This solution made following assumptions:
1. The timestamp of entries in the file is in an increasing order.
2. When calculating the 1.5 hour period with least cars, it will calculate *exact* 1.5 hours. In the file if the contiguous lines span less than 1.5 hours, the program will ignore the lines.

The program has a suite of auto tests using `scalatest`, run `sbt test` to run the full test suite.

This solution contains following classes / objects:
- MainApp: This is the entry point of the application
- TimeFrame: A domain class which represents a line in the file
- TimeFrameParser: Provides functions to parse a string into TimeFrame
- Statistics: Provides functions to calculate different statistics required by the coding challenge.
