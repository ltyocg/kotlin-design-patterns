package action

abstract class Action(val type: ActionType)
class ContentAction(val content: Content) : Action(ActionType.CONTENT_CHANGED)
class MenuAction(val menuItem: MenuItem) : Action(ActionType.MENU_ITEM_SELECTED)