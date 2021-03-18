sex=int(input())
age=int(input())
height=int(input())
weight=int(input())

manBMR = 66 + (13.7 * weight) + (5 * height) - (6.8 * age)
womanBMR = 665 + (9.6 * weight) + (1.8 * height) - (4.7 * age)

status = {"rest":1,"halfWorkout":2,"mediumWorkout":3,"hardWorkout":4,"heavyWorkout":5}

def BMR(bmr):
    


#นั่งทำงานอยู่กับที่ และไม่ได้ออกกำลังกายเลย = BMR x 1.2    // bmr*1.2
#ออกกำลังกายหรือเล่นกีฬาเล็กน้อย ประมาณอาทิตย์ละ 1-3 วัน = BMR x 1.375 // bmr*1.375
#ออกกำลังกายหรือเล่นกีฬาปานกลาง ประมาณอาทิตย์ละ 3-5 วัน = BMR x 1.55 // bmr*1.55
#ออกกำลังกายหรือเล่นกีฬาอย่างหนัก ประมาณอาทิตย์ละ 6-7 วัน = BMR x 1.725 // bmr*1.725
#ออกกำลังกายหรือเล่นกีฬาอย่างหนักทุกวันเช้าเย็น = BMR x 1.9 // bmr*1.9

