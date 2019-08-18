Welcome to the ETHOS Team practical test!

The goal of this exercise is to test your general problem solving and
programming ability. You may choose any language and technology stack with which
you are comfortable.

Problem
-------
Dulu is a fictional OTT internet television service provider. Imagine that, you
are tasked to design an Online User Tracking(OUT) system. OUT will be responsible for keeping
track of online viewer count on the different streams/channels available through the service.
(A online viewer is defined as a user who was reported watching a stream
at any point during the last 5 mins)

Dulu has the offering available via a number of platforms which include popular
Android & Apple devices, Apple TV, Android TV, Smart TV, roku etc.
On some platforms the service is available over platform native apps and on some
it uses web browsers to deliver content.

### Part 1: Design

Design an efficient, secure, and scalable system. Your design should be for your
definition of a production-ready system. The design should cover the following
aspects of software

- Describe the key consideration(s).
- Explain persistence strategy for your system.
- What were the design tradeoffs you considered?
- How will your system scale?
- What can fail? How will your system recover?
- What are the security concerns?

Describe your design in [DESIGN.md](DESIGN.md)

### Part 2: Implementation

Develop a set of APIs for OUT, which can be used for tracking and reporting
of the viewer count on the different streams.
The program should support feature & configuration toggles for enabling dynamic
  system behavior.


#### Minimum requirements

Your implementation should have API(s) for tracking.
The API(s) should at least collect the following information for tracking:

- Device Type
- Stream Details
- User/Device Identifier
- Geo Location details

Your implementation should have API(s) for reporting.
The reporting API(s) at minimum should be able to answer the following:

- Current viewer count per stream
- Current viewer count across all streams
- Current viewer count per device type
- Current viewer count by geography(country/state)
- Current stream count per user

#### Sample Test scenarios

**Tracking requests**

1. Tracking Request: User1(CA) streaming content from TNT on Android.
2. Tracking Request: User2(TX) streaming content from ESPN on Apple.
3. Tracking Request: User3(NY) streaming content from ESPN on Apple.
4. Tracking Request: User1(CA) streaming content from ABC on Android.

**Reporting**

1. Current Unique Users streaming content on ESPN.
Response: should return user count as 2.    
2. Current Unique users across all streams.
Response: should return user count as 3.
3. Current Unique users across all streams per device type.
Response: should return user count as 2 for Android & 2 for Apple.
4. Current Unique users across all streams per state.
Response: should return user count as 1 for CA, 1 for NY, and 1 for TX.
5. Current stream counts for User1.
Response: should return stream count for User1 as 2

**Documentation**

Tell us about your implementation in [IMPLEMENTATION.md](IMPLEMENTATION.md).

Include a description of what your implementation would have included if you had
been given more time.

Additional Notes
-----------------
You may use any resource at your disposal (like the internet).

If you have questions open an issue in this repo. We'll be notified and respond
as soon as we can.

Commit early and often - it helps us understand the project’s progression.

We'll remove your username as a collaborator of this repo at [DUE DATE/TIME]
and the test will be finished.

Our team will review your code within 1-2 business days and your Adobe Talent
Partner will contact you after that.
