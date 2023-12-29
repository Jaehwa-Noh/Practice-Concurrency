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
    var body: some View {
        VStack {
            
            ProgressView(value: 0.5) {
                Text("Knight")
            }
            Spacer()
                .frame(height:16)
            ProgressView(value: 0.5) {
                Text("Cavalry")
            }
            
        }
        .padding()
    }
}

struct CallAndReturnAndPauseButtons: View {
    var body: some View {
        HStack {
            Button("Call") {
                
            }
            
            Button("Return") {
                
            }
        }
    }
}

#Preview {
    CastleScreen()
}
