# OpenAI Kotlin Image Search Sample App
This sample android app demonstrates how to use the unofficial [OpenAI Kotlin plugin](https://github.com/aallam/openai-kotlin) to search for images based on user text input and the response is displayed on the recyclerview.

### How It Works

When the app is launched, the user is presented with a text input field where they can enter a search term. When they submit the search query, the app uses the OpenAI Kotlin plugin to search for text that matches the query. It then takes the results of that search and uses them to download images from the internet that are relevant to the search term.

The images are displayed in a RecyclerView, which allows the user to scroll through them. Each image is displayed in a card view that includes the image itself and some information about it, such as the image URL and a brief description.

### Technologies Used

- Kotlin programming language
- OpenAI Kotlin plugin
- RecyclerView
- Android Studio

### Getting Started
Add following in your app level gradle file 
```{gradle}
dependencies {
    // import Kotlin API client BOM
    implementation platform('com.aallam.openai:openai-client-bom:3.2.3')

    // define dependencies without versions
    implementation 'com.aallam.openai:openai-client'
    runtimeOnly 'io.ktor:ktor-client-okhttp'
}
```
### Initializing the open-ai object
```{kotlin}
val openAI = OpenAI(apiKey)
```
you can get the open-ai api key from the platform open ai [site](https://platform.openai.com),
Make sure the api-key is not expired under your account 

### License

This project is licensed under the MIT License. See the LICENSE file for details.

### Contributing

Contributions are welcome! If you'd like to contribute to this project, please submit a pull request or open an issue to discuss your ideas.
