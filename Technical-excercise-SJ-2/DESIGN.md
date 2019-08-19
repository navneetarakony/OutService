Online User Tracker (OUT)
=========================

##1. High level design - 
At a high level this is a client-server architecture where 
1. the client is the end user device running a streaming application and providing the details of the stream at constant intervals 
2. and the server consists of an application service (Stats service) that continuously collects and processes the information sent by the clients.

Client Device - 
1. The client device is responsible for sending all the data regarding the stream to the Stats service at a regular interval as long as the application is running in the device.
2. I chose an interval of 1 mins, so that it is granular enough to make fairly accurate calculations for the stats required.
3. The data contains 
- a unique Id that is assigned to a user
- a device id (this maps to a device type)
- stream id that maps to a particular channel/video
- the location information which for now is tracked by the state and country names.
The above data object helps us to uniquely identify a stream watched by a user on a particular device at all time (here all time - 1 min intervals)

Stats service -
1. The stats service is resposible for performing the heavuweight calculations and provide the statistics.
2. The service can serve requests by exposing a REST api for all the required queries which are translated to a DB query.
3. In this implementation, I have using an in-memory SQL DB H2 to record new entries.
4. The stats service creates new entries in the DB each time a new user starts a session (here session is when a user logs in after 5 mins) or switches streams or starts a new device.
5. If the user is watching a stream on the same device continuously, the DB row is just updated with the latest timestamp. This is to avoid duplicate entries in the DB when users's watch streams for a long duration.
6. Clearing stale DB entries - The DB entries that have the last updated timeStamp greater than 15 mins can be cleared using a scheduler job. This helps in improving the DB lookup time since the number of entries would be limited. These rows that were deleted could be backed up if needed.

Key Considerations - 
1. Client device sending data to the stats service using REST API - The other alternative was to use a websocket. But that would require the stats service to have a many open connects as the number of devices.
2. I am not persisting very granular data about the user in case he/she is not changing the state of the application (ie. stream, device etc). This helps in scaling the DB entries thereby supporting more clients.

Scalability options -
1. A SQL DB can handle upto 200-300k reads, but this is a write heavy system. In case the requirement is to handle more clients at an instant, we can have the following options implemented - 
a. Shard the DB based on location.
b. use a No-SQL location.
c. Use a Write ahead logging before writing to the DB.
2. To scale the stats service, we can implement a cache since in this case, we are anyway considering a 5 mins interval. Therefore consistency is not really a concern. We can compromise consistency for scale/performance.

Failure scenarios -
1. The stats service and the clients are stateless services which can always recover from a failure.
2. The DB is the only stateful service which can have a hot standby to recover its data.
3. The stats service can be scaled horizontally if it reaches its resource limits.



