# Virtual-Health-Assistant

A medical assistant solution that handles simple, repetitive tasks - works on the idea of NLP which detects illness symptoms of user when described, suggests remedies/ directs to a doctor if needed.

First extract the zipped data using the extract_data.py file. After which you can then train the data using the train_data.py file or the file from the notebook directory. After training, a model is generated and saved to the present working directory.

Finally, run the app.py file which loads the saved model and then deploys the model to a server which was built using Flask.
The resulting API can then be consumed by the app.

To run the app, build the project in the Android directory and then you can then run the app which consumes the api from our server.

This is an open-source project work hence, permission is granted, free of charge, to any person to deal in without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute.

