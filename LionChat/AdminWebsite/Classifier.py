from sklearn.naive_bayes import ComplementNB
from sklearn.feature_extraction.text import TfidfVectorizer
from flask import Flask, send_from_directory, request, jsonify
from flask_cors import CORS
import csv

app = Flask(__name__)
CORS(app)

classifier = ComplementNB()
inputVector = TfidfVectorizer()

#Send index.html
@app.route('/', methods=['GET'])
@app.route('/index.html', methods=['GET'])
def get_index():
    #return contents from index.html
    return send_from_directory('', 'index.html', mimetype='text/html')

#Send main.js
@app.route('/main.js', methods=['GET'])
def get_main():
    #return contents from main.js
    return send_from_directory('', 'main.js', mimetype='text/javascript')

def trainIntentClassifier():
    
    #Open questions.csv to train classifier from
    questions = []
    labels = []
    with open("questions.csv", newline='', encoding='utf-8-sig') as csvfile:
        csvReader = csv.DictReader(csvfile, delimiter=',')
        for row in csvReader:
            questions.append(row['Question'].strip())
            labels.append(row['Intent'].strip())
        
    #Transforming questions list into questions tfIDF vectors
    questions_vec = inputVector.fit_transform(questions)
    
    #Training the classifier
    classifier.fit(questions_vec, labels)

@app.route('/intent', methods=['POST'])
def getIntent():
    
    #Get POST request from client
    receivedDict = request.get_json()
    print(receivedDict)
    
    #Get user utterance
    userInput = []
    userInput.append(receivedDict["utterance"])
    
    #Transform user utterance to tfIDF vector
    new_question = inputVector.transform(userInput)
    
    #Classify intent
    intent = classifier.predict(new_question)
    print(intent)
    
    prob = classifier.predict_proba(new_question)
    print(prob)
    prob = prob.item(0) #Gets first probability in list
    
    #If probability is the same across each class, then intent is unknown
    '''
    May be wrong. Might need to update for multilabel classification
    '''
    if(prob == (1 / len(classifier.classes_))):
        intent = ["unknownIntent"]
    
    #Return intent as JSON
    return jsonify(intent=intent[0])
    
if __name__ == "__main__":
    
    #Train classifier upon server startup
    trainIntentClassifier()
    
    #Run server
    app.run(port = 8000)