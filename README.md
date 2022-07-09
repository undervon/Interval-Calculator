# Interval Calculator

### Develop a java Spring Web application with the following configuration:

1. It has one action that does the following:
* receives a set of intervals in JSON format;
* for each interval, it calculates the interval duration;
* for each interval, except the first one, it calculates the duration between the end of the last interval and the start of the new interval;
* returns the response in JSON format as well.
2. Considering that we have an "**Interval**" resource – implement the required REST endpoints to:
* list all intervals;
* view an interval by id;
* add an interval;
* remove an interval.
3. If possible, try securing the endpoint with an **API key** using spring security.

Example input:
[
	 {
		 "id": 0,
		 "start": "24/06/2014 08:22:07",
		 "end": "28/12/2016 12:10:14"
	 },
	 {
		 "id": 1,
		 "start": "15/08/2014 15:38:55",
		 "end": "18/12/2019 08:34:26"
	 },
	 {
		 "id": 2,
		 "start": "06/06/2015 04:12:34",
		 "end": "12/05/2018 08:56:3"
	 {
		 "id": 3,
		 "start": "21/10/2018 14:14:59",
		 "end": "23/03/2017 19:43:55"
	 },
	 {
		 "id": 4,
		 "start": "17/10/2014 16:14:29",
		 "end": "30/01/2015 08:35:50"
	 }
]

Example output:
[
	 {
		 "id": 0,
		 "start": "12/01/2016 10:32:35",
		 "end": "11/10/2018 10:50:03",
		 "duration": "8h20m"
	 },
	 {
		 "id": 1,
		 "start": "20/01/2016 21:29:06",
		 "end": "10/08/2018 17:43:41",
		 "duration": "8h20m",
		 "break": "13h48m"
	 },
	 {
		 "id": 2,
		 "start": "01/04/2018 12:43:15",
		 "end": "31/03/2019 09:31:37",
		 "duration": "8h20m",
		 "break": "13h48m"
	 },
	 {
		 "id": 3,
		 "start": "05/01/2019 11:24:12",
		 "end": "09/11/2018 09:15:33",
		 "duration": "8h20m",
		 "break": "13h48m"
	},
	{
		 "id": 4,
		 "start": "18/01/2020 02:30:58",
		 "end": "26/06/2017 17:10:12",
		 "duration": "8h20m",
		 "break": "13h48m"
	}
]