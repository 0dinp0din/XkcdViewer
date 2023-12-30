# XkcdViewer
 ### Shortcut technical test

 https://github.com/shortcut/coding-assignment-mobile

The app is a functional MVP of what was requested by the customer. It lets the user scroll through comics, see explanations, share and save them. The offline functionality lets the user see saved comics.

### Architecture:

When making the app, I focused making the app architecture clean and expandable. I made separate packages/folders for components, screens, data, viewmodels, etc to easily find relevant files.<br>

Initially, i quickly made a working solution before cleaning up the project. I do this to make it easier for myself to visualize the final product. When a rough working version was made, I moved all data-related and async stuff to viewmodels to make the UI components tidier. <br>I also made components for complex buttons or cards for reusability and to make it easier to work with the "screens".

## Functionality:

#### browse through the comics
The app lets the user browse through comics. I initially wanted to display comics vertically in a feed, but decided to show one at a time due to how the api works. If i wanted to show all the comics in a feed, I would have to loop through all the comics numbers, request each one from the api, and put them in an array in the app. I decided this would be unnecessary.

#### see the comic details, including its description
The API has all this information, so I simply displayed it in the comic-card.

#### search for comics by the comic number as well as text
The search api provided didn't seem to work. I played with the idea of searching for strings on the wiki through webscraping, but ended up not doing it to prioritize the other areas due to time. <br> https://www.explainxkcd.com/wiki/index.php/List_of_all_comics

#### get the comic explanation
This was a tricky one. I didn't find an API on the page, but discovered that the explainxkcd website is built using MediaWiki. I found out they had a built-in API that included a way to parse information on a page <br>
https://www.explainxkcd.com/wiki/api.php?action=help&modules=parse

Using this, i could get the specific section of the explanation like this:<br>
https://www.explainxkcd.com/wiki/api.php?action=parse&page=2172:_Lunar_Cycles&prop=wikitext&sectiontitle=Explanation&format=json

The section has lots of irrelevant information, but I found a pattern where the explanation would start with a "==Explanation==" header, and end with a "==Transcript==". I used this to cut out the part I wanted in the code. 

#### favorite the comics, which would be available offline too
I implemented Room to store information about the comics, and used a bitmap to base64 to store the images. Currently, the user has to be offline for the offline-comics to show. I did this because i thought it made the most sense that the user would view the comics online if possible.

#### send comics to others
I solved this by making a button that copied the url. On some versions of Android the clipboard also recognizes the url and shows a menu to share it through apps.

#### get notifications when a new comic is published
This was another one of the things I ended up not doing due to the time constraint. I have not done this before, so I did some research, and assumed the atom.xml feed could be used for this<br>
https://xkcd.com/atom.xml
