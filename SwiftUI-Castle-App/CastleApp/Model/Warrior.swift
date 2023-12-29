//
//  Warrior.swift
//  CastleApp
//
//  Created by Jaehwa Noh on 12/29/23.
//

protocol Warrior {
    var location: Int { get set }
    var delayTime: Int { get }
    
    func moveToCastle()
    func returnToHome()
}


class Knight: Warrior {
    var location: Int
    var delayTime: Int
    
    init(delayTime: Int) {
        self.location = 0
        self.delayTime = delayTime
    }
    
    func moveToCastle() {
        while location < 100 {
            location += 3
        }
    }
    
    func returnToHome() {
        self.location = 0
    }
}

class Cavalry: Warrior {
    var location: Int
    var delayTime: Int
    
    init(delayTime: Int) {
        self.location = 0
        self.delayTime = delayTime
    }
    
    func moveToCastle() {
        while location < 100 {
            location += 5
        }
    }
    
    func returnToHome() {
        self.location = 0
    }
}
