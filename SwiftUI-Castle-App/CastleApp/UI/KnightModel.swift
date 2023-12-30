//
//  KnightModel.swift
//  CastleApp
//
//  Created by Jaehwa Noh on 12/29/23.
//

import SwiftUI

class Warrior: ObservableObject {
    @Published var location: Int
    var delayTime: Int
    var name: String
    var moveDistance: Int
    
    var whereAreYou: Double {
        let progressValue = Double(location) / 100
        if progressValue >= 1.0 {
            return 1.0
        } else {
            return progressValue
        }
    }
    
    init(delayTime: Int, name: String, moveDistance: Int) {
        self.location = 0
        self.delayTime = delayTime
        self.name = name
        self.moveDistance = moveDistance
    }
    
    func moveToCastle() async throws {
        
        while location <= 100 {
            try Task.checkCancellation()
            try await Task.sleep(for: .milliseconds(delayTime))
            await MainActor.run {
                location += moveDistance
            }
        }
    }
    
    func returnToHome() {
        self.location = 0
    }
}
