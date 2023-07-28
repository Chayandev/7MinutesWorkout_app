package com.example.need7minute

object Constants {
    fun defaultExerciseList():ArrayList<ExerciseModel>{
        val exerciseList=ArrayList<ExerciseModel>()
        val jumpingJacks=ExerciseModel(
            1,
            "Jumping Jacks",
            R.drawable.jumping_jacks,
            false,
            isSelected = false
        )
        exerciseList.add(jumpingJacks)

        val wallSitting=ExerciseModel(
            2,
            "Wall Sitting",
            R.drawable.wall_sit,
            false,
            isSelected = false
        )
        exerciseList.add(wallSitting)

        val pushUp=ExerciseModel(
            3,
            "Push Up",
            R.drawable.push_ups,
            false,
            isSelected = false
        )
        exerciseList.add(pushUp)

        val absCrunches=ExerciseModel(
            4,
            "Abdominal Crunches",
            R.drawable.abdominal_chrunches,
            false,
            isSelected = false
        )
        exerciseList.add(absCrunches)

        val stepUpOntoChair=ExerciseModel(
            5,
            "Step-up Onto a Chair",
            R.drawable.setup_onto_chair,
            false,
            isSelected = false
        )
        exerciseList.add(stepUpOntoChair)

        val squats=ExerciseModel(
            6,
            "Squats",
            R.drawable.squats,
            false,
            isSelected = false
        )
        exerciseList.add(squats)

        val tricepsDips=ExerciseModel(
            7,
            "Triceps Dip",
            R.drawable.triceps_dips,
            false,
            isSelected = false
        )
        exerciseList.add(tricepsDips)

        val plank=ExerciseModel(
            8,
            "Plank",
            R.drawable.plank_image,
            false,
            isSelected = false
        )
        exerciseList.add(plank)

//        val highKnees=ExerciseModel(
//            1,
//            "High knees,Running in Place",
//            R.raw.,
//            false,
//            isSelected = false
//        )
//        exerciseList.add(jumpingJacks)

        val lunges=ExerciseModel(
            9,
            "Alternating Lunges",
            R.drawable.lunges,
            false,
            isSelected = false
        )
        exerciseList.add(lunges)
        val highKnees=ExerciseModel(
            10,
            "High knees,Running in Place",
            R.drawable.high_knees,
            false,
            isSelected = false
        )
        exerciseList.add(highKnees)


        val reversePlank=ExerciseModel(
            11,
            "Reverse plank Kick",
            R.drawable.reverse_plank_kick,
            false,
            isSelected = false
        )
        exerciseList.add(reversePlank)

        val sidePlank=ExerciseModel(
            12,
            "Side plank",
            R.drawable.side_plank,
            false,
            isSelected = false
        )
        exerciseList.add(sidePlank)
        return exerciseList
    }
}