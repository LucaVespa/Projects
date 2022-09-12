This was a summer project that I completed on my own that was intended to be used as the main feature in a dating app that was in the process of being made by one of my suitemates from freshman year. 
It is written in Swift/SwiftUI using XCode as the IDE. 
For security reasons and also for simplicity, I have only included files that contain the actual code, meaning that things like server-side information, XCode settings, and other similar information is not included in this folder.
Despite this, the application I made should be easily reproducible if necessary by copying the code and setting up XCode/server-side settings accordingly.

The aim of this application was to create matches between two people based solely on proximity. The idea was that anyone with the app would be able to match with other people that they passed that also happened to have the app.
Using Firebase alongside Apple's built-in GPS services, I was able to come up with an algorithm that would match you with anyone in a ~150 meter radius, with each ping happening every 3 minutes.
Any matches are stored locally on your device and can be displayed at any given time.

Since the program was purely backend, the UI is very simple. Additionally, since I was using a free plan with Firebase's Realtime Database, I was not able to execute commands directly onto the server and instead had to send certain requests from the client.
This issue is documented in comments a few times in the code and would be an easy fix with the paid version of Firebase. Though the application still functions properly without the paid version of Firebase,
the cloud functions included in the paid plan would make the app much more secure and reliable.

Though some quality-of-life features like preventing repeat matches (this was not addressed due to testing limitation) or clearing the match list are not a part of the app, they are changes that are not too difficult to make
given the current version of the app. The hardest part, which is accurately making matches real-time, is complete without issue.

Aside from the issue with having to pay for certain features of Firebase addressed earlier, the only real limitation of this app is that in order for it to work for each user, the app must be running in the background at all times
(or at least whenever you want to make matches). This is not a problem from a functionality perspective, but from a consumer perspective, it is difficult to determine whether or not people would want to use an app that not only 
uses your location, but must be running at all times.

That said, the application does exactly what it is meant to do and is as efficient as I could possibly make it.