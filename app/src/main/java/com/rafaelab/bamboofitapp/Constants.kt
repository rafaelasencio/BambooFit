package com.rafaelab.bamboofitapp

class Constants {

    companion object {

        fun easyExerciseList():ArrayList<ExerciseModel>{
            val exerciseList = ArrayList<ExerciseModel>()

            val jumpingJacks = ExerciseModel(1, "JumpingJacks", R.drawable.ic_jumping_jacks, false, false)

            exerciseList.add(jumpingJacks)

            val wallSit = ExerciseModel(2, "Wall Sit", R.drawable.ic_wall_sit_rotation, false, false)
            exerciseList.add(wallSit)

            val pushUp = ExerciseModel(3, "Staggered Arm Knee", R.drawable.ic_staggered_arm_knee, false, false)
            exerciseList.add(pushUp)

            val abdominalCrunch = ExerciseModel(4, "Abdominal Crunch", R.drawable.ic_abdominal_crunch, false, false)
            exerciseList.add(abdominalCrunch)

            val stepUpOnChair = ExerciseModel(5, "Step-Up onto Chair", R.drawable.ic_step_up_onto_chair, false, false)
            exerciseList.add(stepUpOnChair)

            val squat = ExerciseModel(6, "Squat", R.drawable.ic_squat, false, false)
            exerciseList.add(squat)

            val tricepsDipOnChair = ExerciseModel(7, "Triceps Dip On Chair", R.drawable.ic_triceps_dip_on_chair, false, false)
            exerciseList.add(tricepsDipOnChair)

            val plank = ExerciseModel(8, "Plank", R.drawable.ic_plank, false, false)
            exerciseList.add(plank)

            val highKneesRunningInPlace = ExerciseModel(9, "High Knees Running In Place", R.drawable.ic_high_knees_running_in_place, false, false)
            exerciseList.add(highKneesRunningInPlace)

            val lunges = ExerciseModel(10, "Lunges", R.drawable.ic_lunges, false, false)
            exerciseList.add(lunges)

            val pushupAndRotation = ExerciseModel(11, "Mountain Climber", R.drawable.ic_mountain_climber, false, false)
            exerciseList.add(pushupAndRotation)

            val sidePlank = ExerciseModel(12, "Jump Squat", R.drawable.ic_jump_squat, false, false)
            exerciseList.add(sidePlank)

            return exerciseList
        }

        fun mediumExerciseList():ArrayList<ExerciseModel>{
            val exerciseList = ArrayList<ExerciseModel>()

            val jumpRope = ExerciseModel(1, "Jump Rope", R.drawable.ic_jump_rope, false, false)

            exerciseList.add(jumpRope)

            val squatJacks = ExerciseModel(2, "Squat Jacks", R.drawable.ic_squat_jacks, false, false)
            exerciseList.add(squatJacks)

            val hinduPushUp = ExerciseModel(3, "Hindu Push Up", R.drawable.ic_hindu_push_ups, false, false)
            exerciseList.add(hinduPushUp)

            val doubleLegStretch = ExerciseModel(4, "Double Leg Stretch", R.drawable.ic_double_leg_stretch, false, false)
            exerciseList.add(doubleLegStretch)

            val burpees = ExerciseModel(5, "Burpees", R.drawable.ic_burpees, false, false)
            exerciseList.add(burpees)

            val sumoSquat = ExerciseModel(6, "Sumo Squat", R.drawable.ic_sumo_squat, false, false)
            exerciseList.add(sumoSquat)

            val singleLegTricepsDips = ExerciseModel(7, "Single Leg Triceps Dips", R.drawable.ic_single_leg_tricep_dips, false, false)
            exerciseList.add(singleLegTricepsDips)

            val plankBirdDog = ExerciseModel(8, "Plank Bird Dog", R.drawable.ic_plank_bird_dog, false, false)
            exerciseList.add(plankBirdDog)

            val heisman = ExerciseModel(9, "Heisman", R.drawable.ic_heisman, false, false)
            exerciseList.add(heisman)

            val dumbbelSwing = ExerciseModel(10, "Dumbbel Swing", R.drawable.ic_dumbbell_swing, false, false)
            exerciseList.add(dumbbelSwing)

            val inchworm = ExerciseModel(11, "Inchworm", R.drawable.ic_inchworm, false, false)
            exerciseList.add(inchworm)

            val inAndOutJacks = ExerciseModel(12, "In and Out Jacks", R.drawable.ic_in_and_out_jacks, false, false)
            exerciseList.add(inAndOutJacks)

            return exerciseList

        }

        fun advancedExerciseList():ArrayList<ExerciseModel>{
            val exerciseList = ArrayList<ExerciseModel>()

            val burpees = ExerciseModel(1, "Burpees", R.drawable.ic_burpees, false, false)

            exerciseList.add(burpees)

            val rollingSquat = ExerciseModel(2, "Rolling Squat", R.drawable.ic_rolling_squat, false, false)
            exerciseList.add(rollingSquat)

            val hinduPushUp = ExerciseModel(3, "Spiderman Push Up", R.drawable.ic_spiderman_push_up, false, false)
            exerciseList.add(hinduPushUp)

            val boatTwist = ExerciseModel(4, "Boat Twist", R.drawable.ic_boat_twist, false, false)
            exerciseList.add(boatTwist)

            val doubleLegDonkeyKicks = ExerciseModel(5, "Double Leg Donkey Kicks", R.drawable.ic_double_leg_donkey_kicks, false, false)
            exerciseList.add(doubleLegDonkeyKicks)

            val squatThrust = ExerciseModel(6, "Squat Thrust", R.drawable.ic_squat_thrust, false, false)
            exerciseList.add(squatThrust)

            val pikePushUp = ExerciseModel(7, "Pike Push Up", R.drawable.ic_pike_push_up, false, false)
            exerciseList.add(pikePushUp)

            val plankKickThroughs = ExerciseModel(8, "Plank Kick Throughs", R.drawable.ic_plank_kick_throughs, false, false)
            exerciseList.add(plankKickThroughs)

            val inAndOutJacks = ExerciseModel(9, "In and Out Jacks", R.drawable.ic_in_and_out_jacks, false, false)
            exerciseList.add(inAndOutJacks)

            val inchworm = ExerciseModel(10, "Inchworm", R.drawable.ic_inchworm, false, false)
            exerciseList.add(inchworm)

            val pushupAndRotation = ExerciseModel(11, "Tabletop Reverse Pike", R.drawable.ic_tabletop_reverse_pike, false, false)
            exerciseList.add(pushupAndRotation)

            val jumpSprintBack = ExerciseModel(12, "Jump and Sprint Back", R.drawable.ic_jump_sprint_back, false, false)
            exerciseList.add(jumpSprintBack)

            return exerciseList
        }
    }
}