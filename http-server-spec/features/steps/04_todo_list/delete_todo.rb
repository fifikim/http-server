require "uri"
require "./features/steps/shared.rb"

class Spinach::Features::DeleteToDo < Spinach::FeatureSteps
  include Shared::Standard

  step 'I make a DELETE request to delete the to-do task' do
    @response = Requests.delete("/todo/1", {
      "Content-Type": "application/json",
      "Accepts": "application/json"
    })
  end

  step 'I make a DELETE request to a to-do item that does not exist' do
    @response = Requests.delete("/todo/1000", {
      "Content-Type": "application/json",
      "Accepts": "application/json"
    })
  end
end
