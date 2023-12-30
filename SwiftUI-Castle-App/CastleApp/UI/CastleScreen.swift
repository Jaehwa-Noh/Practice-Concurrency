//
//  ContentView.swift
//  CastleApp
//
//  Created by Jaehwa Noh on 12/29/23.
//

import SwiftUI

struct CastleScreen: View {
    var body: some View {
        VStack {
            Text("Great Castle")
                .font(.title)
            
            CastleButtonAndWarriorLocation()
        }
    }
}

struct CastleButtonAndWarriorLocation: View {
    //    @StateObject private var castleViewModel = CastleViewModel()
    @StateObject private var knightModel = Warrior(delayTime: 500, name: "Knight", moveDistance: 3)
    @StateObject private var cavalryModel = Warrior(delayTime: 300, name: "Cavalry", moveDistance: 5)
    @State var isCalled = false
    
    var body: some View {
        VStack {
            
            ProgressView(value: knightModel.whereAreYou) {
                Text("\(knightModel.name)")
            }
            Spacer()
                .frame(height:16)
            ProgressView(value: cavalryModel.whereAreYou) {
                Text("\(cavalryModel.name)")
            }
            
            CallAndReturnAndPauseButtons(isCalled: $isCalled, knightModel: knightModel, cavalryModel: cavalryModel)
            
        }
        .padding()
        
    }
}

struct CallAndReturnAndPauseButtons: View {
    @Binding var isCalled: Bool
    //    let castleViewModel: CastleViewModel
    let knightModel: Warrior
    let cavalryModel: Warrior
    @State var callJob: Task<Void, Never>?
    var body: some View {
        HStack {
            Button(isCalled ? "Pause" : "Call") {
                if isCalled {
                    if let callJob = callJob {
                        if !callJob.isCancelled {
                            callJob.cancel()
                        }
                    }
                    isCalled = false
                    
                } else {
                    isCalled = true
                    
                    callJob = Task {
                        do {
                            try await withThrowingTaskGroup(of: Void.self) { group in
                                group.addTask {
                                        try await knightModel.moveToCastle()
                                }
                                group.addTask {
                                    try await cavalryModel.moveToCastle()
                                }
                                try await group.next()
                            }
                            
                        } catch {
                            
                        }
                        isCalled = false
                    }
                }
            }
            
            Spacer()
                .frame(width: 16)
            
            Button("Return") {
                isCalled = false
                if let callJob = callJob {
                    if !callJob.isCancelled {
                        callJob.cancel()
                    }
                }
                knightModel.returnToHome()
                cavalryModel.returnToHome()
            }
        }
    }
}

#Preview {
    CastleScreen()
}
