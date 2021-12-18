import sys
import json
from pymongo import MongoClient

def upload(file_name):

    file = open(file_name)
    data = json.loads(file.read())
    
    print(data)

    client = MongoClient()
    db = client["quiz-database"]
    questions = db["questions"]    

    q = questions.insert_many(data)
    print(q.inserted_ids)


if __name__ == "__main__":
    print(
        "Accepting moduleId"
    )
    
    print ("Number of arguments passed to upload questions {}".format(len(sys.argv)))
    
    assert (len(sys.argv) == 2)

    upload(sys.argv[1])