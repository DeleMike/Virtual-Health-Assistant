import zipfile
import os

path = "dataset"

file = zipfile.ZipFile(os.path.join(path, "medical-question-answer-data.zip"))
file.extractall(path)
file.close()