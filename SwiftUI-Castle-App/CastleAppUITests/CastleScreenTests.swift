//
//  CastleScreenTests.swift
//  CastleAppUITests
//
//  Created by Jaehwa Noh on 12/30/23.
//

import XCTest

final class CastleScreenTests: XCTestCase {
    
    var app: XCUIApplication?
    
    override func setUpWithError() throws {
        try super.setUpWithError()
        // Put setup code here. This method is called before the invocation of each test method in the class.
        
        // In UI tests it is usually best to stop immediately when a failure occurs.
        continueAfterFailure = false
        
        // In UI tests it’s important to set the initial state - such as interface orientation - required for your tests before they run. The setUp method is a good place to do this.
        app = XCUIApplication()
        app?.launch()
    }
    
    override func tearDownWithError() throws {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
        try super.tearDownWithError()
        app = nil
    }
    
    func test_CastleScreen_ClickCall_PauseShowSuccess() {
        let callButton = app!.buttons["Call"]
        XCTAssertTrue(callButton.exists)
        callButton.tap()
        
        let pauseButton = app!.buttons["Pause"]
        XCTAssertTrue(pauseButton.exists)
    }
    
    func test_CastleScreen_ClickCallAndPause_CallShowSuccess() {
        let callButton = app!.buttons["Call"]
        XCTAssertTrue(callButton.exists)
        callButton.tap()
        
        let pauseButton = app!.buttons["Pause"]
        XCTAssertFalse(callButton.exists)
        XCTAssertTrue(pauseButton.exists)
        pauseButton.tap()
        
        XCTAssertTrue(callButton.exists)
    }
    
    func test_CastleScreen_ClickCallAndReturn_CallShowSuccess() {
        let callButton = app!.buttons["Call"]
        XCTAssertTrue(callButton.exists)
        callButton.tap()
        
        let returnButton = app!.buttons["Return"]
        XCTAssertFalse(callButton.exists)
        XCTAssertTrue(returnButton.exists)
        returnButton.tap()
        
        XCTAssertTrue(callButton.exists)
    }
    
    func test_CastleScreen_ClickCallAndReturnTwice_CallShowEdge() {
        for _ in 1...2 {
            let callButton = app!.buttons["Call"]
            XCTAssertTrue(callButton.exists)
            callButton.tap()
            
            let returnButton = app!.buttons["Return"]
            XCTAssertFalse(callButton.exists)
            XCTAssertTrue(returnButton.exists)
            returnButton.tap()
            
            XCTAssertTrue(callButton.exists)
        }
    }
    
    func test_CastleScreen_CallClickAndWaitFull_CallButtonShowAgainSuccess() {
        let callButton = app!.buttons["Call"]
        XCTAssertTrue(callButton.exists)
        callButton.tap()
        
        XCTAssertTrue(callButton.waitForExistence(timeout: 100))
    }
    
    func test_CastleScreen_ClickCallAndPauseAndCallAndWaitFull_CallButtonShowAgainEdge() {
        let callButton = app!.buttons["Call"]
        XCTAssertTrue(callButton.exists)
        callButton.tap()
        
        sleep(2)
        
        let pauseButton = app!.buttons["Pause"]
        XCTAssertFalse(callButton.exists)
        XCTAssertTrue(pauseButton.exists)
        pauseButton.tap()
        
        sleep(2)
        
        XCTAssertTrue(callButton.exists)
        XCTAssertFalse(pauseButton.exists)
        callButton.tap()
        
        XCTAssertTrue(callButton.waitForExistence(timeout: 100))
    }
}
