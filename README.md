# StudyBuddy
The StudyBuddy Android mobile app empowers users to seamlessly and effectively study any subject. With a just a simple photo of the user's study material, the app will swiftly
generate custom flashcards that are ready to be used. That means less time spent reading abstruse textbooks and more time mastering important concepts.

The app is divided into two parts: Optical Character Recognition (OCR) and a Large Language Model (LLM). The OCR implementation was accomplished using the Google ML Kit Text
Recognition v2 API. When the user captures an image, the app sends the image URI (file path address) to a method that handles the text extraction, leveraging functions from the 
Google ML Kit Text Recognition v2 API. The extracted text is then sent to a seperate class that will convert it to flashcards. 

The LLM integration was accomplished by utilizing the power of OpenAI API and the GPT-3 text-davinci-003 model. Once the GPT model is initialized, it receives a prompt to create 
flashcards with the extracted text, and the corresponding response is presented to the user.
