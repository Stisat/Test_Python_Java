import datetime


def getNoteFromFile():
    try:
        collect = {}
        with open('Note.csv', 'r', encoding='utf-8') as file:
            d = 1
            for l in file:
                l = l.replace('\n', '')
                collect[d] = list(l.split(';'))
                d = d + 1
        return collect
    except FileNotFoundError:
        print("Запрашиваемый файл не найден")
        open('Note.csv', 'w', encoding='utf-8')
        return -1



def getNote():
    if len(dictNote) == 0:
        print('Нет заметок')
    else:
        for i in dictNote:
            print(f'№ {dictNote[i][0]}) {dictNote[i][1]} || дата: {dictNote[i][3]}\n \tтекст: 'f"""{dictNote[i][2]}""")


def getNoteLast():
    if len(dictNote) == 0:
        print('Нет заметок')
    else:
        sortDict = {}
        for i in dictNote:
            tempstr = str(f'{dictNote[i][3]}').replace('-', '').replace(' ', '')
            sortDict[int(tempstr)] = str(
                f'№ {dictNote[i][0]}) {dictNote[i][1]} || дата: {dictNote[i][3]}\n \tтекст: ' f"""{dictNote[i][2]}""")
        sortDict = sorted(sortDict.items(), reverse=True)
        for j in sortDict:
            print(f'{j[1]}')


def addFile():
    with open('Note.csv', 'w', encoding='utf-8') as file:
        for k in dictNote:
            if dictNote[k][0] in dictNote:
                dictNote[k][0] = dictNote[k][0] + 1
                file.write(f'{dictNote[k][0]};{dictNote[k][1]};{dictNote[k][2]};{dictNote[k][3]}\n')
            else:
                file.write(f'{dictNote[k][0]};{dictNote[k][1]};{dictNote[k][2]};{dictNote[k][3]}\n')


def addNote():
    s = 1
    nameNote = str(input('Название заметки: '))
    textNote = str(input('Введите текст заметки: '))
    dateNote = str(datetime.datetime.now().strftime("%Y-%m-%d %H-%M-%S"))
    while True:
        if s in dictNote:
            s = s + 1
        else:
            fullNote = str(f'{s}"#"{nameNote}:"#"{textNote}. "#"{dateNote}')
            save = list(fullNote.split('"#"'))
            dictNote[s] = save
            addFile()
            break


def findNote():
    find = str(input('Введите ваш запрос для поиска (регистр учитывается): '))
    for i in dictNote:
        for j in range(len(dictNote[i])):
            if find in dictNote[i][j]:
                print(f'{i}) {dictNote[i]}')


def deleteNote():
    for i in dictNote:
        print(f'{i} {dictNote[i][1]}')
    n = int(input('Введите № заметки для удаления: '))
    dictNote.pop(n)
    addFile()


def choise(dict):
    print('Операции с журналом заметок:')
    print(
        'show - Показать журнал (showlast - в порядке убывания по дате);\nfind - Произвести поиск по запросу;\nadd - Добавить заметку;\ndel - Удаление заметки;\nexit - Выход')
    n = str(input('Ваш выбор: '))
    if n == 'show':
        getNote()
        print('~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~')
        choise(dictNote)
    elif n == 'showlast':
        getNoteLast()
        print('~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~')
        choise(dictNote)
    elif n == 'find':
        findNote()
        choise(dictNote)
    elif n == 'add':
        addNote()
        getNote()
        print('~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~')
        addFile()
        choise(dictNote)
    elif n == 'del':
        deleteNote()
        getNote()
        print('~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~')
        choise(dictNote)
    elif n == 'exit':
        print('До Свидания!')

    else:
        print('Вы сделали неверный выбор, попробуйте еще раз: ')
        choise(dict)


dictNote = getNoteFromFile()
if dictNote==-1:
    print("Приложение завершено. Повторите попытку еще раз.")
else:
    print('Здравствуйте!')
    choise(dictNote)