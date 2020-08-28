from tensorflow.keras.models import load_model
from flask import Flask, jsonify, request
from flask_cors import CORS
import pickle
import numpy as np
import nltk
from nltk import LancasterStemmer
import pandas as pd

stemmer = LancasterStemmer()

app = Flask(__name__)
CORS(app)

model = load_model("chatbot_model.hdf5")

with open('labels.pkl', 'rb') as f:
    labels = pickle.load(f)

with open('words.pkl', 'rb') as f:
    words = pickle.load(f)


def clean_up_sentence(sentence):
    sentence_words = nltk.word_tokenize(sentence)
    sentence_words = [stemmer.stem(word.lower()) for word in sentence_words]
    return sentence_words

def bow(sentence, words, show_details=True):
    sentence_words = clean_up_sentence(sentence)
    bag = [0]*len(words)
    for s in sentence_words:
        for i,w in enumerate(words):
            if w == s:
                bag[i] = 1
                if show_details:
                    print ("found in bag: %s" % w)

    return(np.array(bag))

@app.route('/')
def home_endpoint():
    return 'Hello there, welcome to Team Medic Minds'


@app.route('/api/predict', methods=['GET', 'POST'])
def classify():
    ERROR_THRESHOLD = 0.25

    sentence = request.json['sentence']
    input_data = pd.DataFrame([bow(sentence, words)], dtype=float, index=['input']).to_numpy()
    results = model.predict([input_data])[0]
    results = [[i, r] for i, r in enumerate(results) if r > ERROR_THRESHOLD]
    results.sort(key=lambda x: x[1], reverse=True)
    return_list = []
    for r in results:
        return_list.append({"intent": labels[r[0]], "probability": str(r[1])})

    response = jsonify(return_list)
    return response

if __name__ == '__main__':

    app.run(host='0.0.0.0', port=5000)


'''
def get_prediction():
    sentence = request.json['sentence']
    
    if flask.request.method == 'POST':
        data = flask.request.json  # Get data posted as a json
        if data == None:
            data = flask.request.args

        input = data.get('data')
        prediction = predictStringInput(chatbot_model,input)

    return prediction

'''