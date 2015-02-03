# Social Auth Custom

 This is a custom version of social auth. By using this we provide tweets in  Twitter and post in FaceBook.
 
 Late we can Implement all other social media by changing  < Provider."social media name">
 
 eg: Provider.FACEBOOK
 
## For custom post in Facebook we can use

``` 
 adapter.updateStory(  "Hello SocialAuth Android" ,  "Google SDK for Android",
             "Build great social apps and get more installs.",
             "The Facebook SDK for Android makes it easier and faster to develop Facebook integrated 
                                             Android apps.",  "https://www.facebook.com",               
                                            "http://carbonfreepress.gr/images/facebook.png", new MessageListener());                
   }
```