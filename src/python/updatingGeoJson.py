import json


def addPrecincts(fileName):

    precincts = {};
    with open(fileName) as f:
        data = json.load(f)
    length = len(data['features'])
    for i in range(0, length):
        properties = data['features'][i]['properties']
        p = {
            'ID': properties['ID'],
            'area': properties['area'],
            'population': properties['population']
        }
        precincts[i] = p
    return precincts




def createMassachussets():
    state = {}
    state['name'] = "MASSACHUSETTS"
    state['ID'] = '0'
    state['numOfPrecincts'] = '2165'
    state['unassigned'] = {}
    state['unassigned']['precincts']  = addPrecincts('MassachusettsFinal.json')

    with open('massachusettsSerialized.json', 'w') as outfile:
        json.dump(state, outfile)

def createOhio():
    state = {}
    state['name'] = "OHIO"
    state['ID'] = '1'
    state['numOfPrecincts'] = '11029'
    state['unassigned'] = {}
    state['unassigned']['precincts']  = addPrecincts('OhioFinal.json')

    with open('ohioSerialized.json', 'w') as outfile:
        json.dump(state, outfile)


def createOregon():
    state = {}
    state['name'] = "OREGON"
    state['ID'] = '1'
    state['numOfPrecincts'] = '1221'
    state['unassigned'] = {}
    state['unassigned']['precincts'] = addPrecincts('OregonFinal.json')

    with open('oregonSerialized.json', 'w') as outfile:
        json.dump(state, outfile)
