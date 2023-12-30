//
//  WarriorModelTest.swift
//  CastleAppTests
//
//  Created by Jaehwa Noh on 12/30/23.
//

import XCTest
@testable import CastleApp

class WarriorModelTests: XCTestCase {
    private let warriorModel: Warrior = Warrior(delayTime: 300, name: "Cavalry", moveDistance: 5)
    
    func test_WarriorModel_CreateWarrior_InitValueCheckSucesse() {
        XCTAssertEqual(warriorModel.name, "Cavalry")
        XCTAssertEqual(warriorModel.delayTime, 300)
        XCTAssertEqual(warriorModel.moveDistance, 5)
    }
    
    func test_WarriorModel_MoveToCastle_LocationChangeSuccess() async throws {
        try await withThrowingTaskGroup(of: Void.self) { group in
            group.addTask {
                try await self.warriorModel.moveToCastle()
            }
            group.addTask {
                try await Task.sleep(for: .seconds(1))
            }
            let _ = try await group.next()
            group.cancelAll()
        }
        
        let expectLocation = 15
        XCTAssertEqual(expectLocation, warriorModel.location)
    }
    
    func test_WarriorModel_MoveToCastleAndReturnToHome_LocationInitEdge() async throws {
        try await withThrowingTaskGroup(of: Void.self) { group in
            group.addTask {
                try await self.warriorModel.moveToCastle()
            }
            group.addTask {
                try await Task.sleep(for: .seconds(1))
            }
            let _ = try await group.next()
            group.cancelAll()
        }
        
        let expectLocation = 15
        XCTAssertEqual(expectLocation, warriorModel.location)
        
        warriorModel.returnToHome()
        XCTAssertEqual(0, warriorModel.location)
    }
    
    func test_WarriorModel_MoveToCastle_WhereAreYouFullSuccess() async throws {
        
        try await warriorModel.moveToCastle()
        
        XCTAssertEqual(1.0, warriorModel.whereAreYou)
    }
}
