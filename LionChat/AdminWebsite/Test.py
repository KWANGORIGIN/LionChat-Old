import csv

questions = []
labels = []
with open("questions.csv", newline='', encoding='utf-8-sig') as csvfile:
    csvReader = csv.DictReader(csvfile, delimiter=',')
    for row in csvReader:
        questions.append(row['Question'].strip())
        labels.append(row['Intent'].strip())
        
print(questions)
print(labels)